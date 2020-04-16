<?php

namespace RestoNurseryBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * SaveResto
 *
 * @ORM\Table(name="save_resto")
 * @ORM\Entity
 */
class SaveResto
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
     * @var string
     *
     * @ORM\Column(name="etat", type="string", length=10, nullable=true)
     */
    private $etat;



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

    /**
     * @return string
     */
    public function getEtat()
    {
        return $this->etat;
    }

    /**
     * @param string $etat
     */
    public function setEtat($etat)
    {
        $this->etat = $etat;
    }






}

