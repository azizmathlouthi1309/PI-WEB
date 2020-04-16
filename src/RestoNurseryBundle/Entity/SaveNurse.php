<?php

namespace RestoNurseryBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * SaveNurse
 *
 * @ORM\Table(name="save_nurse")
 * @ORM\Entity
 */
class SaveNurse
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
     *
     * @ORM\Column(name="date_begin", type="string", length=10, nullable=false)
     */
    private $dateBegin;

    /**
     * @var string
     *
     * @ORM\Column(name="date_end", type="string", length=10, nullable=false)
     */
    private $dateEnd;

    /**
     * @ORM\OneToOne(targetEntity="FirstBundle\Entity\Child", cascade={"persist"})
     * @ORM\JoinColumn(name="Child_id", referencedColumnName="id")
     */
    private $idChild;

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
    public function getDateBegin()
    {
        return $this->dateBegin;
    }

    /**
     * @param string $dateBegin
     */
    public function setDateBegin($dateBegin)
    {
        $this->dateBegin = $dateBegin;
    }

    /**
     * @return string
     */
    public function getDateEnd()
    {
        return $this->dateEnd;
    }

    /**
     * @param string $dateEnd
     */
    public function setDateEnd($dateEnd)
    {
        $this->dateEnd = $dateEnd;
    }

    /**
     * @return mixed
     */
    public function getIdChild()
    {
        return $this->idChild;
    }

    /**
     * @param mixed $idChild
     */
    public function setIdChild($idChild)
    {
        $this->idChild = $idChild;
    }




}

