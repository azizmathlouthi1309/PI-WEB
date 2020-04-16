<?php

namespace ClassBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Doctrine\ORM\Mapping\ManyToOne;
use Doctrine\ORM\Mapping\JoinColumn;
use Symfony\Component\Validator\Constraints as Assert;
/**
 * Class
 *
 * @ORM\Table(name="class")
 * @ORM\Entity
 */
class Classe
{
    /**
     * @var integer
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var string
     *@Assert\NotBlank
     * @ORM\Column(name="name", type="string", length=255, nullable=false)
     */
    private $name;

    /**
     * @var integer
     *
     * @ORM\Column(name="nb_child", type="integer", nullable=false)
     *  @Assert\Range(
     *      min = 1,
     *      max = 25,
     *      minMessage = "You must be at least {{ limit }} ",
     *      maxMessage = "Class maximum is  {{ limit }}")
     */
    private $nbChild;

    /**
     * @var integer
     * @Assert\NotBlank
     * @ORM\Column(name="level", type="integer", nullable=false)
     */
    private $level;



    /**
     *
     * @ManyToOne(targetEntity="AppBundle\Entity\User", inversedBy="ActivityInterest")
     * @JoinColumn(name="teacher_id", referencedColumnName="id")
     */
    private $idteacher;

    /**
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * @param int $id
     */
    public function setId($id)
    {
        $this->id = $id;
    }

    /**
     * @return string
     */
    public function getName()
    {
        return $this->name;
    }

    /**
     * @param string $name
     */
    public function setName($name)
    {
        $this->name = $name;
    }

    /**
     * @return int
     */
    public function getNbChild()
    {
        return $this->nbChild;
    }

    /**
     * @param int $nbChild
     */
    public function setNbChild($nbChild)
    {
        $this->nbChild = $nbChild;
    }

    /**
     * @return int
     */
    public function getLevel()
    {
        return $this->level;
    }

    /**
     * @param int $level
     */
    public function setLevel($level)
    {
        $this->level = $level;
    }

    /**
     *
     * Get teacher
     *
     * @return \FirstBundle\Entity\User
     */
    public function getIdteacher()
    {
        return $this->idteacher;
    }


    /**
     * @param mixed $idteacher
     */
    public function setIdteacher($idteacher)
    {
        $this->idteacher = $idteacher;
    }

    public function __toString()
    {
        return $this->name;
    }
}

